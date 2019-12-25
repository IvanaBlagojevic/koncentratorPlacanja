import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MainPageComponent } from './main-page/main-page.component';
import { AppComponent } from './app.component';
import { PaymentErrorComponent } from './payment-error/payment-error.component';
import { PaymentSuccessComponent } from './payment-success/payment-success.component';

const appRoutes: Routes = [
  {path: '', component : MainPageComponent},
  {path: 'error', component : PaymentErrorComponent},
  {path: 'success', component : PaymentSuccessComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    MainPageComponent,
    PaymentErrorComponent,
    PaymentSuccessComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(
      appRoutes,
      {enableTracing : true}
    )
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
