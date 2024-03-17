console.log("edit");

(() => {
    let editSubmitBtn = document.querySelector("#edit-submit-btn");
    let editForm = document.querySelector("#edit-new-user-form");
    editSubmitBtn.addEventListener("click", e => {
        e.preventDefault();

        let formInputs = editForm.querySelectorAll(".form-control");

        let sendObj = {
            id: formInputs[0].value,
            firstName: formInputs[1].value,
            lastName: formInputs[2].value,
            age: formInputs[3].value,
            username: formInputs[4].value,
            password: formInputs[5].value,
            roles: Array
                .from(formInputs[6]
                    .querySelectorAll("option"))
                .filter(e => e.selected)
                .map(e => e.value)
        }
        sendData(
            "http://localhost:8080/api/v1/admin/user",
            "PUT",
            sendObj
        )
            .then(errors => {
                if (errors) {
                    executeErrorsFormFill(document.querySelector("#edit-new-user-form"), errors);
                } else {
                    loadUsers().then(data => fillTable(usersTbodyElem, tableRowElementTemplate, data))
                        .then(() => {
                            initFormAutoFill();
                            document.querySelector("#close-edit-modal-btn").click();
                            hideAllErrors(document.querySelector("#edit-new-user-form"));
                        })
                }
            })
    })

    document.querySelector("#edit-user-close-btn").addEventListener("click", e => {
        hideAllErrors(document.querySelector("#edit-new-user-form"));
    });
})();