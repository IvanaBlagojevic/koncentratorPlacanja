package com.example.bitcoinService;

import javax.annotation.PostConstruct;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.MoreExecutors;
import org.bitcoinj.core.*;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.wallet.SendRequest;
import org.bitcoinj.wallet.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * This class is responsible for downloading the blockchain and sending and receiving money.
 *
 */

@Component
public class MyWallet {
	
	@Autowired
	private WalletAppKit walletAppKit;		//perform receiving and sending money
	@Autowired
	private NetworkParameters networkParameters;
	
	@PostConstruct
	public void start() {
	    walletAppKit.startAsync();	//start of blockchain download 
	    walletAppKit.awaitRunning(); //block any other operations before startAsync() finishes
	    
	    //listener for receiving money
	    walletAppKit.wallet().addCoinsReceivedEventListener(
	    		  (wallet, tx, prevBalance, newBalance) -> {
	    		    Coin value = tx.getValueSentToMe(wallet);
	    		    
	    		    System.out.println("Received tx for " + value.toFriendlyString());
	    		    
	    		    //we use just 1 confirmation for the transaction to be valid
	    		  Futures.addCallback(tx.getConfidence().getDepthFuture(1), 
	    		  new FutureCallback<TransactionConfidence>() {
	    		    @Override
	    		    public void onSuccess(TransactionConfidence result) {
	    		    	
	    		      System.out.println("Received tx " +
	    		         value.toFriendlyString() + " is confirmed. ");
	    		      
	    		    }

	    		    @Override
	    		      public void onFailure(Throwable t) {}
	    		  }, MoreExecutors.directExecutor());
	    		});
	    
	    Address sendToAddress = LegacyAddress.fromKey(networkParameters,    
	      walletAppKit.wallet().currentReceiveKey());
	    System.out.println("Send coins to: " + sendToAddress);
	}
	
	
	public void send(String value, String to) {
	    try {
	        Address toAddress = 
	          LegacyAddress.fromBase58(networkParameters, to); //1
	        SendRequest sendRequest = SendRequest.to(toAddress, 
	          Coin.parseCoin(value)); //2
	        sendRequest.feePerKb = Coin.parseCoin("0.0005"); //3
	        Wallet.SendResult sendResult = 
	          walletAppKit.wallet().sendCoins(walletAppKit.peerGroup(), 
	          sendRequest); //4
	        sendResult.broadcastComplete.addListener(() ->
	          System.out.println("Sent coins onwards! Transaction hash is " + sendResult.tx.getTxId()),
	            MoreExecutors.directExecutor()); //5
	    } catch (InsufficientMoneyException e) {
	        throw  new RuntimeException(e);
	    }
	}

}
