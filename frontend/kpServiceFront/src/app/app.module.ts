import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { HomepageComponent } from './homepage/homepage.component';
import { PayPalSuccessComponent } from './pay-pal-success/pay-pal-success.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BitcoinSuccessComponent } from './bitcoin-success/bitcoin-success.component';
import { BitcoinCancelComponent } from './bitcoin-cancel/bitcoin-cancel.component';
import { PayPalErrorComponent } from './pay-pal-error/pay-pal-error.component';
import { RedirectionComponent } from './redirection/redirection.component';

const appRoutes: Routes = [ {path: '', component : HomepageComponent},
                            {path : 'ppsuccess', component : PayPalSuccessComponent},
                            {path :  'error', component : PayPalErrorComponent},
                            {path : 'redirection', component : RedirectionComponent},
                            {path : 'bitcoinCancel/:oid', component : BitcoinCancelComponent},
                            {path : 'bitcoinSuccess/:oid', component : BitcoinSuccessComponent}
 ]

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    PayPalSuccessComponent,
    BitcoinSuccessComponent,
    BitcoinCancelComponent,
    PayPalErrorComponent,
    RedirectionComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(
      appRoutes,
      {enableTracing : true}
    ),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
