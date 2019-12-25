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

const appRoutes: Routes = [ {path: ':id1/:id2', component : HomepageComponent},
                            {path : 'ppsuccess', component : PayPalSuccessComponent},
                            {path : 'bitcoinCancel/:oid', component : BitcoinCancelComponent},
                            {path : 'bitcoinSuccess/:oid', component : BitcoinSuccessComponent}
 ]

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    PayPalSuccessComponent,
    BitcoinSuccessComponent,
    BitcoinCancelComponent
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
