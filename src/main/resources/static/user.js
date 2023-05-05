const URL = "http://localhost:8080/api/users"
const URL_ROLES = "http://localhost:8080/api/roles"

const userInfo = document.querySelector('tbody')
let result = ''

function sendRequest(url) {
    return fetch(url)
        .then(response => {
            return response.json()
                .then(error => console.log(error))
        })
}


const selectRoleForm = document.getElementById('roles');

fetch(URL_ROLES)
    .then(res => res.json())
    .then(data => {
        console.log(data)
        let options = '';
        for (const [k, v] of Object.entries(data)) {
            options += `<option value="${Number(k) + 1}">${v.name}</option>`;
        }
        selectRoleForm.innerHTML = options;
        console.log(options)
    })




fetch(URL)
    .then(users => users.json())
    .then(data => insertDataTable(data))
    .catch(error => console.log(error))

const usersTable = document.getElementById('users-table')
const listAllUsers = (users) => {
    users.forEach(user => {
        roleLet = '';
        user.roles.forEach((role) => roleLet += role.nameToString + " ");
        output += `<tr>
                <th><p>${user.id} </p></th>
                <th><p>${user.firstName} </p></th>
                <th><p>${user.lastName} </p></th>
                <th><p>${user.age} </p></th>
                <th><p>${user.email} </p></th>
                <th><p>${roleLet}</p></th>                        
                <th>
                    <button type="button" class="btn btn-primary" data-toggle="modal"
                        data-target="#editModal" id="editButton" data-uid=${user.id}>Edit
                    </button>
                </th>
                <th>
                    <button type="button" class="btn btn-danger" data-toggle="modal"
                        data-target="#deleteModal" id="deleteButton" data-uid=${user.id}>Delete
                    </button>
                </th>
        </tr>`;
    });
    usersTable.innerHTML = output;
}
const insertDataTable = (users) => {
    users.forEach(user => {
        roleLet = '';
        user.roles.forEach((role) => roleLet += role[role] + " ");
        result += `
                <tr>
                        <td>${user['id']}</td>
                        <td>${user['username']}</td>
                        <td>${user['surname']}</td>
                        <td>${user['age']}</td>
                        <td>${user['email']}</td>
                        <td>${roleLet}</td>
<!--                        <td>${user['roles'][0]['name']}</td>-->
                <tr/>
                 `
    });
    userInfo.innerHTML = result
}


async function getData(URL) {
    const data = await fetch(URL)
    return data
}



const table = document.getElementById("userTable")

const dataTable = sendRequest(URL)
for (key in dataTable) {
    let row = document.createElement('tr')
    row.innerHTML = `<td>${key}</td>`
}

