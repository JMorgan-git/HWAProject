'use strict'

//Selector
let resultsDiv = document.querySelector("#results");
let resultsDiv2 = document.querySelector("#results2");
let resultsDiv3 = document.querySelector("#results3");
let getBtn = document.querySelector("#get");
let getByIdBtn = document.querySelector("#getById");
let getByNameBtn = document.querySelector("#getByName");
let postBtn = document.querySelector("#post");
let deleteBtn = document.querySelector("#delete");
let updateBtn = document.querySelector("#put");
let newDiv1 = document.createElement("div1");

//GET - READ
let getRequest = () => {
    axios.get("http://localhost:8080/entries/getAll")
        .then((response) => {
            resultsDiv.innerHTML = "";
            resultsDiv2.innerHTML = "";
            resultsDiv3.innerHTML = "";
            console.log(response);
            displayResult(response.data);
        })
        .catch((err) => {
            console.error(err);
        });
}
//GET - GetById
let getByIdRequest = () => {
    axios.get(`http://localhost:8080/entries/getById/${getId.value}`)
        .then((response) => {
            resultsDiv.innerHTML = "";
            resultsDiv2.innerHTML = "";
            resultsDiv3.innerHTML = "";
            console.log(response);
            const aDiv = document.createElement("div");
            const table = document.createElement("table");
            table.classList.add("fill");
            const th = document.createElement("th");
            th.classList.add("eh");
            const tr = document.createElement("tr");
            tr.classList.add("ho");
            const td = document.createElement("td");
            td.classList.add("he");
            const p = document.createElement("p");
            const text = document.createTextNode(`${response.data.entryId}`); 
            const text2 = document.createTextNode(`${response.data.name} : ${response.data.entry}`);
            const text3 = document.createTextNode(`Start at :  ${response.data.startTime} Taking ${response.data.expectedDuration} mins`);

            th.appendChild(text);
            table.appendChild(th);
            aDiv.appendChild(table);
    
            tr.appendChild(text2);
            table.appendChild(tr);
            aDiv.appendChild(table);
    
            td.appendChild(text3);
            table.appendChild(td);
            aDiv.appendChild(table);
    
            resultsDiv.appendChild(aDiv);
        })
        .catch((err) => {
            console.error(err);
        });
}

//GET - GetByName
let getByNameRequest = () => {
    axios.get(`http://localhost:8080/entries/getByName/${getName.value}`)
        .then((response) => {
            resultsDiv.innerHTML = "";
            resultsDiv2.innerHTML = "";
            resultsDiv3.innerHTML = "";
            console.log(response);
            displayResult(response.data);
        })
        .catch((err) => {
            console.error(err);
        });
}

//POST - CREATE
let postRequest = () => {

    let obj = {
        "name": addName.value,
        "entry": addEntry.value,
        "startTime": addStartTime.value,
        "expectedDuration": addExpectedDuration.value,
    }

    axios.post("http://localhost:8080/entries/create", obj)
        .then((response) => {
            console.log(response);
            getRequest();
        })
        .catch((err) => {
            console.error(err);
        });
}
//PUT/PATCH - UPDATE
let updateRequest = () => {
    let obj = {
        "name": addName.value,
        "entry": addEntry.value,
        "startTime": addStartTime.value,
        "expectedDuration": addExpectedDuration.value,
    }

    axios.put(`http://localhost:8080/entries/update/${useId.value}`, obj)
        .then((response) => {
            console.log(response);
            getRequest();
        })
        .catch((err) => {
            console.error(err);
        });
}
//DELETE - DELETE
let deleteRequest = () => {
    axios.delete(`http://localhost:8080/entries/delete/${entryId.value}`)
        .then((response) => {
            console.log(response);
            getRequest();
        })
        .catch((err) => {
            console.error(err);
        });
}

let count = 0;
let displayResult = (data) => {
    for (let ent of data) {
        const aDiv = document.createElement("div");
        const table = document.createElement("table");
        table.classList.add("fill");
        const th = document.createElement("th");
        th.classList.add("eh");
        const tr = document.createElement("tr");
        tr.classList.add("ho");
        const td = document.createElement("td");
        td.classList.add("he");
        const p = document.createElement("p");
        const text = document.createTextNode(`${ent.entryId}`);
        const text2 = document.createTextNode(`${ent.name} : ${ent.entry}`);
        const text3 = document.createTextNode(`Start at : ${ent.startTime} Taking ${ent.expectedDuration} mins`);

        th.appendChild(text);
        table.appendChild(th);
        aDiv.appendChild(table);

        tr.appendChild(text2);
        table.appendChild(tr);
        aDiv.appendChild(table);

        td.appendChild(text3);
        table.appendChild(td);
        aDiv.appendChild(table);     
        
        console.log(count);
        if(count == 0){
            resultsDiv.appendChild(aDiv);
        }else if(count == 1){
            resultsDiv2.appendChild(aDiv);
        }else if(count == 2){
            resultsDiv3.appendChild(aDiv);
            count = count -3;
        }
        count++;
        
    }
}

getBtn.addEventListener("click", getRequest);
getByIdBtn.addEventListener("click", getByIdRequest);
getByNameBtn.addEventListener("click", getByNameRequest);
postBtn.addEventListener("click", postRequest);
deleteBtn.addEventListener("click", deleteRequest);
updateBtn.addEventListener("click", updateRequest);