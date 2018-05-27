import { Component, OnInit } from "@angular/core";
import { SportsService } from "~/services/sports.service";

@Component({
    selector: "Home",
    moduleId: module.id,
    templateUrl: "./home.component.html"
})
export class HomeComponent implements OnInit {

    public sportsList: Array<Object> = [];

    constructor(private _sportService: SportsService) {

    }

    ngOnInit(): void {
        this.listSports();
    }

    public listSports() {
        //Call UserActivityData
        this._sportService.getSports().subscribe(
            result => {
                if (result.length > 0) {
                    this.sportsList = this.getListSports(result);
                } else {
                    this.sportsList = [];
                }
            },
            error => {
                console.log(<any>error);
            }
        );
    }

    public getListSports(responseArray) {
        let auxList: Array<any> = [];
        for (let i = 0; i <= responseArray.length; i++) {
                auxList.push(responseArray[i].activity);
        }
        return auxList;
    }
}
