"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var app_routing_module_1 = require("./app-routing.module");
var app_component_1 = require("./app.component");
var http_1 = require("@angular/common/http");
var core_1 = require("@angular/core");
var nativescript_module_1 = require("nativescript-angular/nativescript.module");
var sports_service_1 = require("~/services/sports.service");
var AppModule = /** @class */ (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        core_1.NgModule({
            declarations: [
                app_component_1.AppComponent,
            ],
            imports: [
                nativescript_module_1.NativeScriptModule,
                app_routing_module_1.AppRoutingModule,
                http_1.HttpClientModule,
            ],
            schemas: [
                core_1.NO_ERRORS_SCHEMA
            ],
            bootstrap: [app_component_1.AppComponent],
            providers: [sports_service_1.SportsService]
        })
    ], AppModule);
    return AppModule;
}());
exports.AppModule = AppModule;
