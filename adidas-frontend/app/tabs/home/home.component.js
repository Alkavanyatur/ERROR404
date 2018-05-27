"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var sports_service_1 = require("~/services/sports.service");
var HomeComponent = /** @class */ (function () {
    function HomeComponent(_sportService) {
        this._sportService = _sportService;
        this.sportsList = [];
    }
    HomeComponent.prototype.ngOnInit = function () {
        this.listSports();
    };
    HomeComponent.prototype.listSports = function () {
        var _this = this;
        //Call UserActivityData
        this._sportService.getSports().subscribe(function (result) {
            if (result.length > 0) {
                _this.sportsList = _this.getListSports(result);
            }
            else {
                _this.sportsList = [];
            }
        }, function (error) {
            console.log(error);
        });
    };
    HomeComponent.prototype.getListSports = function (responseArray) {
        var auxList = [];
        for (var i = 0; i <= responseArray.length; i++) {
            auxList.push(responseArray[i].activity);
        }
        return auxList;
    };
    HomeComponent = __decorate([
        core_1.Component({
            selector: "Home",
            moduleId: module.id,
            templateUrl: "./home.component.html"
        }),
        __metadata("design:paramtypes", [sports_service_1.SportsService])
    ], HomeComponent);
    return HomeComponent;
}());
exports.HomeComponent = HomeComponent;
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiaG9tZS5jb21wb25lbnQuanMiLCJzb3VyY2VSb290IjoiIiwic291cmNlcyI6WyJob21lLmNvbXBvbmVudC50cyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiOztBQUFBLHNDQUFrRDtBQUNsRCw0REFBMEQ7QUFPMUQ7SUFJSSx1QkFBb0IsYUFBNEI7UUFBNUIsa0JBQWEsR0FBYixhQUFhLENBQWU7UUFGekMsZUFBVSxHQUFrQixFQUFFLENBQUM7SUFJdEMsQ0FBQztJQUVELGdDQUFRLEdBQVI7UUFDSSxJQUFJLENBQUMsVUFBVSxFQUFFLENBQUM7SUFDdEIsQ0FBQztJQUVNLGtDQUFVLEdBQWpCO1FBQUEsaUJBY0M7UUFiRyx1QkFBdUI7UUFDdkIsSUFBSSxDQUFDLGFBQWEsQ0FBQyxTQUFTLEVBQUUsQ0FBQyxTQUFTLENBQ3BDLFVBQUEsTUFBTTtZQUNGLEVBQUUsQ0FBQyxDQUFDLE1BQU0sQ0FBQyxNQUFNLEdBQUcsQ0FBQyxDQUFDLENBQUMsQ0FBQztnQkFDcEIsS0FBSSxDQUFDLFVBQVUsR0FBRyxLQUFJLENBQUMsYUFBYSxDQUFDLE1BQU0sQ0FBQyxDQUFDO1lBQ2pELENBQUM7WUFBQyxJQUFJLENBQUMsQ0FBQztnQkFDSixLQUFJLENBQUMsVUFBVSxHQUFHLEVBQUUsQ0FBQztZQUN6QixDQUFDO1FBQ0wsQ0FBQyxFQUNELFVBQUEsS0FBSztZQUNELE9BQU8sQ0FBQyxHQUFHLENBQU0sS0FBSyxDQUFDLENBQUM7UUFDNUIsQ0FBQyxDQUNKLENBQUM7SUFDTixDQUFDO0lBRU0scUNBQWEsR0FBcEIsVUFBcUIsYUFBYTtRQUM5QixJQUFJLE9BQU8sR0FBZSxFQUFFLENBQUM7UUFDN0IsR0FBRyxDQUFDLENBQUMsSUFBSSxDQUFDLEdBQUcsQ0FBQyxFQUFFLENBQUMsSUFBSSxhQUFhLENBQUMsTUFBTSxFQUFFLENBQUMsRUFBRSxFQUFFLENBQUM7WUFDekMsT0FBTyxDQUFDLElBQUksQ0FBQyxhQUFhLENBQUMsQ0FBQyxDQUFDLENBQUMsUUFBUSxDQUFDLENBQUM7UUFDaEQsQ0FBQztRQUNELE1BQU0sQ0FBQyxPQUFPLENBQUM7SUFDbkIsQ0FBQztJQWxDUSxhQUFhO1FBTHpCLGdCQUFTLENBQUM7WUFDUCxRQUFRLEVBQUUsTUFBTTtZQUNoQixRQUFRLEVBQUUsTUFBTSxDQUFDLEVBQUU7WUFDbkIsV0FBVyxFQUFFLHVCQUF1QjtTQUN2QyxDQUFDO3lDQUtxQyw4QkFBYTtPQUp2QyxhQUFhLENBbUN6QjtJQUFELG9CQUFDO0NBQUEsQUFuQ0QsSUFtQ0M7QUFuQ1ksc0NBQWEiLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgeyBDb21wb25lbnQsIE9uSW5pdCB9IGZyb20gXCJAYW5ndWxhci9jb3JlXCI7XHJcbmltcG9ydCB7IFNwb3J0c1NlcnZpY2UgfSBmcm9tIFwifi9zZXJ2aWNlcy9zcG9ydHMuc2VydmljZVwiO1xyXG5cclxuQENvbXBvbmVudCh7XHJcbiAgICBzZWxlY3RvcjogXCJIb21lXCIsXHJcbiAgICBtb2R1bGVJZDogbW9kdWxlLmlkLFxyXG4gICAgdGVtcGxhdGVVcmw6IFwiLi9ob21lLmNvbXBvbmVudC5odG1sXCJcclxufSlcclxuZXhwb3J0IGNsYXNzIEhvbWVDb21wb25lbnQgaW1wbGVtZW50cyBPbkluaXQge1xyXG5cclxuICAgIHB1YmxpYyBzcG9ydHNMaXN0OiBBcnJheTxPYmplY3Q+ID0gW107XHJcblxyXG4gICAgY29uc3RydWN0b3IocHJpdmF0ZSBfc3BvcnRTZXJ2aWNlOiBTcG9ydHNTZXJ2aWNlKSB7XHJcblxyXG4gICAgfVxyXG5cclxuICAgIG5nT25Jbml0KCk6IHZvaWQge1xyXG4gICAgICAgIHRoaXMubGlzdFNwb3J0cygpO1xyXG4gICAgfVxyXG5cclxuICAgIHB1YmxpYyBsaXN0U3BvcnRzKCkge1xyXG4gICAgICAgIC8vQ2FsbCBVc2VyQWN0aXZpdHlEYXRhXHJcbiAgICAgICAgdGhpcy5fc3BvcnRTZXJ2aWNlLmdldFNwb3J0cygpLnN1YnNjcmliZShcclxuICAgICAgICAgICAgcmVzdWx0ID0+IHtcclxuICAgICAgICAgICAgICAgIGlmIChyZXN1bHQubGVuZ3RoID4gMCkge1xyXG4gICAgICAgICAgICAgICAgICAgIHRoaXMuc3BvcnRzTGlzdCA9IHRoaXMuZ2V0TGlzdFNwb3J0cyhyZXN1bHQpO1xyXG4gICAgICAgICAgICAgICAgfSBlbHNlIHtcclxuICAgICAgICAgICAgICAgICAgICB0aGlzLnNwb3J0c0xpc3QgPSBbXTtcclxuICAgICAgICAgICAgICAgIH1cclxuICAgICAgICAgICAgfSxcclxuICAgICAgICAgICAgZXJyb3IgPT4ge1xyXG4gICAgICAgICAgICAgICAgY29uc29sZS5sb2coPGFueT5lcnJvcik7XHJcbiAgICAgICAgICAgIH1cclxuICAgICAgICApO1xyXG4gICAgfVxyXG5cclxuICAgIHB1YmxpYyBnZXRMaXN0U3BvcnRzKHJlc3BvbnNlQXJyYXkpIHtcclxuICAgICAgICBsZXQgYXV4TGlzdDogQXJyYXk8YW55PiA9IFtdO1xyXG4gICAgICAgIGZvciAobGV0IGkgPSAwOyBpIDw9IHJlc3BvbnNlQXJyYXkubGVuZ3RoOyBpKyspIHtcclxuICAgICAgICAgICAgICAgIGF1eExpc3QucHVzaChyZXNwb25zZUFycmF5W2ldLmFjdGl2aXR5KTtcclxuICAgICAgICB9XHJcbiAgICAgICAgcmV0dXJuIGF1eExpc3Q7XHJcbiAgICB9XHJcbn1cclxuIl19