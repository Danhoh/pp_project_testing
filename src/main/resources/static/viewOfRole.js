console.log("viewOfRole");

(() => {
        loadPrincipal()
            .then(principal => {
                if (!principal[0]["roles"].find(str => str === "ADMIN")) {
                    document.querySelector("#user-side-btn").click();
                    document.querySelector("#admin-side-btn").remove();
                }
            })
})();