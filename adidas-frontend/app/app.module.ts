import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import {HttpClientModule} from '@angular/common/http'
import { NgModule, NgModuleFactoryLoader, NO_ERRORS_SCHEMA } from "@angular/core";
import { NativeScriptModule } from "nativescript-angular/nativescript.module";
import { SportsService } from "~/services/sports.service";

@NgModule({
    declarations: [
        AppComponent,
    ],
    imports: [
        NativeScriptModule,
        AppRoutingModule,
        HttpClientModule,
    ],
    schemas: [
        NO_ERRORS_SCHEMA
    ],
    bootstrap: [AppComponent],
    providers: [SportsService]
})
export class AppModule { }
