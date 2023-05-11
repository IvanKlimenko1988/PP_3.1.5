const urlUser = "/user/currentUser/"

const userInfo = document.querySelector('tbody')

currentUser = fetch(urlUser)
    .then(response => response.json())
    .catch(error => console.log(error));

currentUser.then(user => {
        let roles = ''
        user.roles.forEach(role => {
            roles += role.name + '\n'
        })
        document.getElementById("navbar-email").innerHTML = user.email
        document.getElementById("navbar-roles").innerHTML = roles
    }
)

currentUser.then(user => {
        let roles = ''
        user.roles.forEach(role => {
            roles += role.name.replace("ROLE_", "") + '\n'
        })

        let result = ''
        result += `
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.surname}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td>${roles}</td>
                </tr>`

        userInfo.innerHTML = result
        document.getElementById("admin-user-info").innerHTML = result
    })