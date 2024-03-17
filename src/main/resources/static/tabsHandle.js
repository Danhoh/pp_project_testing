console.log("tabsHandle");

(() => {
    const ADD_USER_FORM_CLASS_HIDDEN = "col-4 mx-auto d-none";
    const ADD_USER_FORM_CLASS_SHOWN = "col-4 mx-auto";
    const USER_TABLE_CLASS_SHOWN = "table table-inner table-striped px-3";
    const USER_TABLE_CLASS_HIDDEN = "table table-inner table-striped px-3 d-none";

    let newUserBtn = document.querySelector("#new-user-btn");
    let userTableBtn = document.querySelector("#user-table-btn");

    let pageHeadingElem = document.querySelector("#page-heading");
    let newUserFormElem = document.querySelector("#new-user-form");
    let userTableElem = document.querySelector("#user-table");


    userTableBtn.onclick = e => {
        e.preventDefault();
        pageHeadingElem.innerHTML = "All users";

        newUserFormElem.setAttribute("class", ADD_USER_FORM_CLASS_HIDDEN);
        userTableElem.setAttribute("class", USER_TABLE_CLASS_SHOWN);

        userTableBtn.setAttribute('class', 'nav-link active');
        newUserBtn.setAttribute('class', 'nav-link');
    };

    newUserBtn.onclick = e => {
        e.preventDefault();
        pageHeadingElem.innerHTML = "Add new user";

        newUserFormElem.setAttribute("class", ADD_USER_FORM_CLASS_SHOWN);
        userTableElem.setAttribute("class", USER_TABLE_CLASS_HIDDEN);

        userTableBtn.setAttribute('class', 'nav-link');
        newUserBtn.setAttribute('class', 'nav-link active');
    };
})();
