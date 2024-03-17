console.log("tabsVisability");

(() => {
    let TABS_CLASS_SHOWN = "nav nav-tabs";
    let TABS_CLASS_HIDDEN = "nav nav-tabs d-none";


    let tabsElem = document.querySelector("#main-container .nav-tabs");
    let adminSideBtn = document.querySelector("#admin-side-btn");
    let userSideBtn = document.querySelector("#user-side-btn");

    adminSideBtn.addEventListener("click", e => {
        tabsElem.setAttribute("class", TABS_CLASS_SHOWN);
    });

    userSideBtn.addEventListener("click", e => {
        tabsElem.setAttribute("class", TABS_CLASS_HIDDEN);
    });
})();