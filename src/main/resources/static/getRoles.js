(() => {
    async function loadRoles() {
        try {
            const response = await fetch('http://localhost:8080/api/v1/admin/roles');

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            return await response.json();
        } catch (error) {
            console.error('Fetch error:', error);

            return [];
        }
    }

    function fillRoleSelect(elem, roles) {

        roles.forEach(role => {
            let optionalElem = document.createElement("option");

            optionalElem.innerHTML = role.split("ROLE_").join("");
            optionalElem.setAttribute("value", role);
            elem.appendChild(optionalElem);
        });
    }

    loadRoles()
        .then(roles => {
            let editUserRolesElem = document.querySelector("#edit-role");
            let addUserRolesElem = document.querySelector("#roles");
            let deleteUserRolesElem = document.querySelector("#delete-role");

            fillRoleSelect(editUserRolesElem, roles);
            fillRoleSelect(addUserRolesElem, roles);
            fillRoleSelect(deleteUserRolesElem, roles);
        })
})();