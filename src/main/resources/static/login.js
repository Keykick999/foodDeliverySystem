document.querySelectorAll("input").forEach(function (ele) {
  ele.onfocus = function () {
    document.querySelectorAll("input").forEach(function (ele) {
      ele.style.cssText = "border:1px solid rgb(218, 218, 218)";
    });
    this.style.cssText = "border-left:2px solid blue;";
  };
  ele.onblur = function () {
    document.querySelectorAll("input").forEach(function (ele) {
      ele.style.cssText = "border:1px solid rgb(218, 218, 218)";
    });
  };
});

let user1 = document.querySelector("#first");
let user2 = document.querySelector("#second");
let age = document.querySelector("#age");
let email = document.querySelector("#email");
let country = document.querySelector("#country");

document.forms[0].onsubmit = function (e) {
  let uservalid1 = false;
  let uservalid2 = false;
  let agevalid = false;
  let emailvalid = false;
  let countryvalid = false;

  if (
    user1.value !== "" &&
    user1.value.length <= 10 &&
    user1.value.length > 2
  ) {
    uservalid1 = true;
  }
  if (
    user2.value !== "" &&
    user2.value.length <= 10 &&
    user2.value.length > 2
  ) {
    uservalid2 = true;
  }
  if (age.value !== "") {
    agevalid = true;
  }
  if (
    email.value !== "" &&
    email.value.split("").includes("@") &&
    email.value
      .split("")
      .slice(email.value.indexOf("@"), email.value.length - 1).length >= 2
  ) {
    emailvalid = true;
  }
  if (country.value !== "" && country.value.length >= 3) {
    countryvalid = true;
  }

  if (
    uservalid1 === false ||
    uservalid2 === false ||
    agevalid === false ||
    emailvalid === false ||
    countryvalid === false
  ) {
    e.preventDefault();
  }
};
