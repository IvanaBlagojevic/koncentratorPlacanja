package com.example.payPalService.controller;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.payPalService.domain.UserPayPal;
import com.example.payPalService.service.PayPalService;
import com.example.payPalService.service.UserPayPalService;
import com.paypal.api.payments.Agreement;
import com.paypal.api.payments.Currency;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.MerchantPreferences;
import com.paypal.api.payments.Patch;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PaymentDefinition;
import com.paypal.api.payments.Plan;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@RestController
public class PomocniController {

	@Autowired
	private PayPalService payPalService;
	
	@Autowired
	private UserPayPalService userService;
	//radi, kreira plan i ugovor
public String createBillingPlan(UserPayPal user) {
		
		try {

			APIContext context = new APIContext(user.getClientId(), user.getClientSecret(), "sandbox");


				Plan plan = new Plan();
				plan.setName("Billing plan for subscription for " + user.getUsername());
				plan.setDescription("Billing plan");
				plan.setType("INFINITE"); //zakucano za sada

				//payment_definitions
				PaymentDefinition paymentDefinition = new PaymentDefinition();
				paymentDefinition.setName("Regular Payments");
				paymentDefinition.setType("REGULAR");
				paymentDefinition.setFrequency("MONTH"); //uplata na mesecnom nivou
				paymentDefinition.setFrequencyInterval("2"); //placanje na svaka dva meseca
				paymentDefinition.setCycles("0");  // broj krugova nisam sigurna sta predstavlja jos uvek

				//currency
				Currency currency = new Currency();
				currency.setCurrency("USD");
				currency.setValue(Double.toString(1.0));
				paymentDefinition.setAmount(currency);

//				//charge_models
//				ChargeModels chargeModels = new com.paypal.api.payments.ChargeModels();
//				chargeModels.setType("SHIPPING");
//				chargeModels.setAmount(currency);
//				List<ChargeModels> chargeModelsList = new ArrayList<ChargeModels>();
//				chargeModelsList.add(chargeModels);
//				paymentDefinition.setChargeModels(chargeModelsList);

				List<PaymentDefinition> paymentDefinitionList = new ArrayList<PaymentDefinition>();
				paymentDefinitionList.add(paymentDefinition);
				plan.setPaymentDefinitions(paymentDefinitionList);

				//merchant_preferences
				MerchantPreferences merchantPreferences = new MerchantPreferences();
				merchantPreferences.setSetupFee(currency);
				merchantPreferences.setSetupFee(currency);
				merchantPreferences.setCancelUrl("https://localhost:1234/cancel"); //url koji se poziva ako dodje do prekida
				merchantPreferences.setReturnUrl("https://localhost:1234/activateAgreement?username="+user.getUsername() + "&callbakUrl=https://localhost:4202?returnUrl=https://localhost:4202");	//url koji se poziva nakon uspesnos, ovde setovati url za nc
				merchantPreferences.setMaxFailAttempts("0");
				merchantPreferences.setAutoBillAmount("YES");				//automatska naplata
				merchantPreferences.setInitialFailAmountAction("CONTINUE");	//sta raditi prilikom otkaza
				plan.setMerchantPreferences(merchantPreferences);

				Plan createdPlan = plan.create(context);

				List<Patch> patchRequestList = new ArrayList<Patch>();
				Map<String, String> value = new HashMap<String, String>();
				value.put("state", "ACTIVE");

				Patch patch = new Patch();
				patch.setPath("/");
				patch.setValue(value);
				patch.setOp("replace");
				patchRequestList.add(patch);

				createdPlan.update(context, patchRequestList);


			//Create BillingAgreement
			Agreement agreement = new Agreement();
			agreement.setName("Base PaypalAgreement");
			agreement.setDescription("Basic PaypalAgreement");
			agreement.setStartDate("2020-01-25T9:45:04Z");

			// Set plan ID
			Plan plan1 = new Plan();
			plan1.setId(createdPlan.getId());
			agreement.setPlan(plan1);

			// Add payer details
			Payer payer = new Payer();
			payer.setPaymentMethod("paypal");
			agreement.setPayer(payer);

			agreement = agreement.create(context);

			for (Links links : agreement.getLinks()) {
				if ("approval_url".equals(links.getRel())) {
					return links.getHref();
				}
			}

		} catch(PayPalRESTException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return "Error";

	}
}
