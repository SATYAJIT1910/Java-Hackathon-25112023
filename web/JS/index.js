const loginText = document.querySelector(".title-text .login");
const loginForm = document.querySelector("form.login");
const loginBtn = document.querySelector("label.login");
const signupBtn = document.querySelector("label.signup");
const signupLink = document.querySelector("form .signup-link a");
signupBtn.onclick = (() => {
    loginForm.style.marginLeft = "-50%";
    loginText.style.marginLeft = "-50%";
});
loginBtn.onclick = (() => {
    loginForm.style.marginLeft = "0%";
    loginText.style.marginLeft = "0%";
});
signupLink.onclick = (() => {
    signupBtn.click();
    return false;
});
function validateForm(event, formType) {
    event.preventDefault();
    var email = document.querySelector(`.${formType} input[type="text"]`).value;
    var password = document.querySelector(`.${formType} input[type="password"]`).value;

    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!emailRegex.test(email)) {
        displayErrorMessage("Invalid email address");
        return false;
    }

    if (password === "") {
        displayErrorMessage("Password field is required");
        return false;
    }
    if (formType === 'signup') {
        var confirmPassword = document.querySelector(`.${formType} input[type="password"][placeholder="Confirm password"]`).value;
        if (password !== confirmPassword) {
            displayErrorMessage("Passwords do not match");
            return false;
        }
    }
    $.ajax({
        url:"RegisterServlet",
        type:"POST",
        data: form,
        success:function(data,textStatus,jqXHR){
                    console.log(data);
                    window.location = "Application.jsp";
        },
        error: function(jqXHR,textStatus,errorThrown){
            console.log(jqXHR);
        },
        processData: false,
        contentType: false
    });
}

function displayErrorMessage(message) {
    var errorMessageElement = document.getElementById("error-message");
    errorMessageElement.innerText = message;
    errorMessageElement.style.display = "block";

    setTimeout(function () {
        errorMessageElement.style.display = "none";
    }, 5000);
}
