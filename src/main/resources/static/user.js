const URL = "http://localhost:8080/api/users"

const userInfo = document.querySelector('tbody')
let result = ''
// const userId = document.getElementById('id')
// const userName = document.getElementById('userName')
// const surname = document.getElementById('surName')
// const year = document.getElementById('year')
// const email = document.getElementById('email')
// const role = document.getElementById('roles')



const insertDataTable = (users) => {
    users.forEach(user => {
        result += `
                <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.surname}</td>
                        <td>${user.age}</td>
                        <td>${user.email}</td>
                        <td>${user.roles}</td>
                <tr/>
                 `
    });
    userInfo.innerHTML = result
}
fetch(URL)
    .then(res => res.json())
    .then(dataTable => insertDataTable(dataTable))
    .catch(error => console.log(error))


//
// let user_info = document.getElementById("user-info");
// let user_table = document.getElementById("userTable");
//
// let td_table = user_table.getElementsByTagName('td');
//
// for(let td of td_table) {
//     td.setAttribute();
// }
