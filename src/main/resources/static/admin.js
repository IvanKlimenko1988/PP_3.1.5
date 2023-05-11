const urlUsers = "/admin/users";
const urlRoles = "/admin/roles";

const allRoles = fetch(urlRoles)
    .then(response => response.json())

const renderTable = (users) => {
    let result = ''
    for (const i in users) {
        let roles = ''
        users[i].roles.forEach(role => {
            roles += role.name.replace("ROLE_", "") + '\n'
        })
        result +=
            `
                <tr>
                    <td>${users[i].id}</td>
                    <td>${users[i].username}</td>
                    <td>${users[i].surname}</td>
                    <td>${users[i].age}</td>
                    <td>${users[i].email}</td>
                    <td>${roles}</td>
                    <td>
                        <button class="btn btn-info btn-sm text-white" data-bs-toogle="modal"
                        data-bs-target="#editModal"
                        onclick="editModalData(${users[i].id})">Edit</button>
                    </td>
                    <td>
                        <button class="btn btn-danger btn-sm" data-bs-toogle="modal"
                        data-bs-target="#deleteModal"
                        onclick="deleteModalData(${users[i].id})">Delete</button>        
                    </td>
                </tr>`
    }
    document.getElementById("users-info-table").innerHTML = result

}

const fillRoles = function (elementId) {
    allRoles.then(roles => {
        let result = ''
        for (const i in roles) {
            result += `<option value = ${roles[i].id}>${roles[i].name.replace("ROLE_", "")}</option>`
        }
        document.getElementById(elementId).innerHTML = result
    })
}

function addPost() {
    const userFormNew = document.getElementById("newUserForm")
    let role = document.getElementById('roleSelectAdd')
    let rolesAddUser = []
    let rolesAddUserValue = ''
    for (let i = 0; i < role.options.length; i++) {
        if (role.options[i].selected) {
            rolesAddUser.push({id: role.options[i].value, name: 'ROLE_' + role.options[i].innerHTML})
            rolesAddUserValue += role.options[i].innerHTML + ' '
        }
    }
    const user = {
        username: document.getElementById('username').value,
        surname: document.getElementById('surname').value,
        age: document.getElementById('age').value,
        email: document.getElementById('email').value,
        password: document.getElementById('password').value,
        roles: rolesAddUser
    }

    fetch(urlUsers, {
        method: 'post',
        body: JSON.stringify(user),
        headers: {
            'Content-type': 'application/json',
        },
    })
        .then(res => {return res.json()})
        .then(data => {
            console.log(data)
            fetch(urlUsers)
                .then(res => res.json())
                .then(data => renderTable(data))
            userFormNew.reset()
            $('[href="#home"]').tab('show');
        })
}

fetch(urlUsers)
    .then(res => res.json())
    .then(data => renderTable(data))


fillRoles("roleSelectAdd")

const modalEditClose = document.getElementById('editCloseBtn')

modalEditClose.addEventListener('click', () => {
    $('#editModal').modal('hide');
})
const modalDeleteClose = document.getElementById("deleteCloseBtn")
modalDeleteClose.addEventListener('click', ()=> {
    $('#deleteModal').modal('hide');
})


const userFormNew = document.getElementById("newUserForm")
userFormNew.addEventListener('submit', function (e) {
    e.preventDefault();
    addPost();

})

async function editModalData(id) {
    $('#editModal').modal('show');
    fillRoles("roleSelectEdit")
    const urlDataEd = 'http://localhost:8080/admin/users/' + id;
    let usersPageEd = await fetch(urlDataEd);
    if (usersPageEd.ok) {
        await usersPageEd.json().then(user => {
            id_ed.value = `${user.id}`;
            firstName_ed.value = `${user.username}`;
            lastName_ed.value = `${user.surname}`;
            year_ed.value = `${user.age}`;
            email_ed.value = `${user.email}`;
            roleSelectEdit.value = `${user.role}`;
        })
    } else {
        alert(`Error, ${usersPageEd.status}`)
    }
}

async function editUser() {
    let urlEdit = 'http://localhost:8080/admin/users/' + id_ed.value;

    let role = document.getElementById('roleSelectEdit')
    let rolesAddUser = []
    let rolesAddUserValue = ''
    for (let i = 0; i < role.options.length; i++) {
        if (role.options[i].selected) {
            rolesAddUser.push({id: role.options[i].value, name: 'ROLE_' + role.options[i].innerHTML})
            rolesAddUserValue += role.options[i].innerHTML + ' '
        } else {

        }
    }
    let method = {
        method: 'PATCH',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            id: form_ed.id_ed.value,
            username: form_ed.firstName_ed.value,
            surname: form_ed.lastName_ed.value,
            age: form_ed.year_ed.value,
            email: form_ed.email_ed.value,
            password: form_ed.password_ed.value,
            roles: rolesAddUser

        })
    }
    await fetch(urlEdit, method).then(() => {
        $('#editCloseBtn').click();
        $('#editModal').modal('hide');
        fetch(urlUsers)
            .then(res => res.json())
            .then(data => renderTable(data))
    })
}




const form_del = document.getElementById('formForDeleting');
const form_ed = document.getElementById('formForEditing');
const id_del = document.getElementById('id_del');
const firstName_del = document.getElementById(`firstName_del`);
const lastName_del = document.getElementById('lastName_del');
const year_del = document.getElementById('year_del');
const email_del = document.getElementById('email_del');


async function deleteModalData(id) {
    $('#deleteModal').modal('show');
    const urlForDel = 'http://localhost:8080/admin/users/' + id;
    let usersPageDel = await fetch(urlForDel);
    if (usersPageDel.ok) {
        await usersPageDel.json().then(user => {
            id_del.value = `${user.id}`;
            firstName_del.value = `${user.username}`;
            lastName_del.value = `${user.surname}`;
            year_del.value = `${user.age}`;
            email_del.value = `${user.email}`;
        })
    } else {
        alert(`Error, ${usersPageDel.status}`)
    }
}

async function deleteUser() {
    let urlDel = 'http://localhost:8080/admin/users/' + id_del.value;
    let method = {
        method: 'DELETE',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            username: form_del.firstName.value,
            surname: form_del.lastName.value,
            age: form_del.year_del.value,
            email: form_del.email_del.value,
        })
    }
    await fetch(urlDel, method)
        .then(() => {
        $('#deleteCloseBtn').click();
            fetch(urlUsers)
                .then(res => res.json())
                .then(data => renderTable(data))
            $('[href="#home"]').tab('show');
    })
}



