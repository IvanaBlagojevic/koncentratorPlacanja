import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterMerchantComponent } from './register-merchant/register-merchant.component';
import { ErrorComponent } from './error/error.component';
import { SuccessComponent } from './success/success.component';
import { FailedComponent } from './failed/failed.component';

const appRoutes: Routes = [
  {path: '', component : RegisterMerchantComponent},
  {path: 'success', component : SuccessComponent},
  {path: 'failed', component : FailedComponent},
  {path: 'error', component : ErrorComponent}
]


@NgModule({
  declarations: [
    AppComponent,
    RegisterMerchantComponent,
    ErrorComponent,
    SuccessComponent,
    FailedComponent
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
