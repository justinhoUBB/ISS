import {extendObservable } from 'mobx';

class UserStore{
    constructor() {
        extendObservable(this,{
            loading: true,
            isLoggendIn: false,
            useremail: ''
        })
    }
}

export default new UserStore();