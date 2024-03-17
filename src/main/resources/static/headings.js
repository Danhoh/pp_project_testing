console.log("headings");

(() => {
    let h1Elem = document.querySelector("h1");
    let h2Elem = document.querySelector("h2");
    let adminSideBtn = document.querySelector("#admin-side-btn");
    let userSideBtn = document.querySelector("#user-side-btn");

    adminSideBtn.addEventListener("click", e => {
        h1Elem.innerHTML = "Admin panel";
        h2Elem.innerHTML = "All users";
    });

    userSideBtn.addEventListener("click", e => {
        h1Elem.innerHTML = "User information-page";
        h2Elem.innerHTML = "About user";
    });
})();