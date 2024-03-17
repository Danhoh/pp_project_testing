console.log("deleteUser");

(() => {
    let deleteBtn = document.querySelector("#delete-submit-btn");
    let deleteForm = document.querySelector("#delete-new-user-form");

    deleteBtn.addEventListener("click", e => {
        e.preventDefault();
        let id = deleteForm.querySelector("#delete-hidden-input").value;

        sendData(
            "http://localhost:8080/api/v1/admin/user/" + id,
            "DELETE"
        ).then(resp => {
            loadUsers().then(data => fillTable(usersTbodyElem, tableRowElementTemplate, data).then(() => initFormAutoFill()))
                .then(resp => {
                    document.querySelector("#close-delete-modal-btn").click(); // click on close modal btn
                })
        })
    });
})();