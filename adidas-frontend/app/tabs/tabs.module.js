"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var common_1 = require("nativescript-angular/common");
var browse_component_1 = require("./browse/browse.component");
var home_component_1 = require("./home/home.component");
var search_component_1 = require("./search/search.component");
var tabs_routing_module_1 = require("./tabs-routing.module");
var tabs_component_1 = require("./tabs.component");
var TabsModule = /** @class */ (function () {
    function TabsModule() {
    }
    TabsModule = __decorate([
        core_1.NgModule({
            imports: [
                common_1.NativeScriptCommonModule,
                tabs_routing_module_1.TabsRoutingModule
            ],
            declarations: [
                tabs_component_1.TabsComponent,
                home_component_1.HomeComponent,
                browse_component_1.BrowseComponent,
                search_component_1.SearchComponent,
            ],
            schemas: [
                core_1.NO_ERRORS_SCHEMA
            ]
        })
    ], TabsModule);
    return TabsModule;
}());
exports.TabsModule = TabsModule;
