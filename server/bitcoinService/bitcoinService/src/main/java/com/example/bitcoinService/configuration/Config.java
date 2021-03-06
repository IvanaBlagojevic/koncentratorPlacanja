package com.example.bitcoinService.configuration;

import java.io.File;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.RegTestParams;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.utils.BriefLogFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*@Configuration
public class Config {
	
	@Value("${bitcoin.network}")
	private String network;
	
	@Value("${bitcoin.file-prefix}")
	private String filePrefix;
	
	@Value("${bitcoin.file-location}")
	private String btcFileLocation;
	
	private NetworkParameters networkParameters;
	
	public Config() {
        BriefLogFormatter.init();
    }
	
	@Bean
	public NetworkParameters networkParameters() {
	    if(network.equals("testnet")) {
	        return TestNet3Params.get();
	    } else if(network.equals("regtest")) {
	        return RegTestParams.get();
	    } else {
	        return MainNetParams.get();
	    }
	}
	
	@Bean
	public WalletAppKit walletAppKit(@Autowired NetworkParameters networkParameters) {
	    //network params, where to store files, optional string to be prefix for any file
		return new WalletAppKit(networkParameters, 
	      new File(btcFileLocation), filePrefix) {
	        @Override
	        protected void onSetupCompleted() {
	        	//check if file has al least one key, and if note we add a fresh one
	            if (wallet().getKeyChainGroupSize() < 1) {
	                wallet().importKey(new ECKey());
	            }
	        }
	    };
	}

}*/
