var tarmac_people = new Vue({
    el: '#tarmac_people',
    data: {
        roleFilter: "",
        cityFilter: "",
        pageNumber:0,
        peopleByPage:8,
        people:[],
        roles:[],
        cities:[]
    },
    methods: {
        previousPage () {
            if(this.pageNumber > 0){
                this.pageNumber -= 1;
            }
        },
        nextPage () {
            this.pageNumber += 1;
        },
        search () {
            if(this.roleFilter !== "" && this.cityFilter !== ""){
                this.fetchPeopleByRoleAndCity();
            }else if(this.roleFilter !== "" && this.cityFilter === ""){
                this.fetchPeopleByRole();
            }else if(this.roleFilter === "" && this.cityFilter !== ""){
                this.fetchPeopleByCity();
            }else{
                this.fetchAllPeople();
            }
        },
        fetchAllPeople(){
            fetch('http://localhost:8080/people/?limit=' + this.peopleByPage +
                '&start=' + (this.pageNumber * this.peopleByPage))
                .then(response => response.json())
                .then(json => {
                    this.setPeople(json);
                })
        },
        fetchPeopleByRoleAndCity(){
            fetch('http://localhost:8080/people/roles/' + this.roleFilter +
                '/cities/' + this.cityFilter + '?limit=' + this.peopleByPage +
                '&start=' + (this.pageNumber * this.peopleByPage))
                .then(response => response.json())
                .then(json => {
                    this.setPeople(json);
                })
        },
        fetchPeopleByRole(){
            fetch('http://localhost:8080/people/roles/' + this.roleFilter +
                '?limit=' + this.peopleByPage + '&start=' + (this.pageNumber * this.peopleByPage))
                .then(response => response.json())
                .then(json => {
                    this.setPeople(json);
                })
        },
        fetchPeopleByCity(){
            fetch('http://localhost:8080/people/cities/' + this.cityFilter +
                '?limit=' + this.peopleByPage + '&start=' + (this.pageNumber * this.peopleByPage))
                .then(response => response.json())
                .then(json => {
                    this.setPeople(json);
                })
        },
        setPeople(people){
            this.people = people;
        }
    },
    created: function () {
        fetch('http://localhost:8080/roles')
            .then(response => response.json())
            .then(json => {
                this.roles = json
            });
        fetch('http://localhost:8080/cities')
            .then(response => response.json())
            .then(json => {
                this.cities = json
            })
    }
});