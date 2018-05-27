import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable()
export class SportsService {
    public url: string = 'http://172.16.30.140:8080/userActivityData/idUser/';

    constructor(public http: HttpClient){

    }

    getSports(): Observable<any>{
        return this.http.get(this.url + '1');

    }
}