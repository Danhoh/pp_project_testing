console.log("addUserForm");

(() => {
    let addUserForm = document.querySelector("#new-user-form");
    let formInputs = addUserForm.querySelectorAll(".form-control");
    let submitBtn = addUserForm.querySelector("#submit-new-user-form-btn");

    submitBtn.addEventListener("click", e => {
        e.preventDefault();
        let submitObj = {
            firstName: formInputs[0].value,
            lastName: formInputs[1].value,
            age: formInputs[2].value,
            username: formInputs[3].value,
            password: formInputs[4].value,
            roles: Array
                .from(formInputs[5]
                    .querySelectorAll("option"))
                .filter(e => e.selected)
                .map(e => e.value)
        }

        sendData(
            "http://localhost:8080/api/v1/admin/user/add",
            "POST",
            submitObj
        )
            .then(resp => {
                if (resp) {
                    executeErrorsFormFill(document.querySelector("#new-user-form"), resp);
                } else {
                    loadUsers().then(data => fillTable(usersTbodyElem, tableRowElementTemplate, data));
                    document.querySelector("#user-table-btn").click();
                    formInputs.forEach(e => e.value = "");
                    hideAllErrors();
                }
            })
    });
})();