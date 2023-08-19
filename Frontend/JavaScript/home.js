let pops = document.getElementById("pop");
let inp = document.getElementById("inp");

function openpopup() {
  let email = document.getElementById("inp").value;
  fetch("http://localhost:8080/travel/subscriber", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: {
      email: email,
    },
  })
    .then((data) => {
      if (data.ok == false) {
        notify("Enter valid email");
      }
    })
    .then((dat) => {
      if (dat != undefined) pops.classList.add("open-pop");
    });
}
function closepopup() {
  pops.classList.remove("open-pop");
}
let nid = document.getElementById("notify");

function notify(message) {
  nid.innerHTML = message;
  nid.style.opacity = 1;
  setInterval(() => {
    nid.style.opacity = 0;
  }, 2000);
}
let bid = document.getElementById("mc");
let rgst = document.getElementById("rf");
let btt = document.getElementById("cd");
function signIn() {
  // bid.innerHTML = null;
  let div = document.createElement("div");

  div.innerHTML = `
  <h2>Welcome to login form</h2>
  <form id="frm" action="javascript:void(0);">
  <i  id="cb" class="fa-solid fa-circle-xmark"></i>
    <div class="inputElement">
      <label for="userName"><h4>Enter the user name</h4></label> <br>
      <input type="text" name="user" id="userName" />
    </div>
    <div class="inputElement">
      <label for="userPass"><h4>Enter the user password</h4></label><br>
      <input type="text" name="env" id="userPass" />
    </div>

    <input id="sb" type="submit" value="Login" class="sub" />
  </form>`;
  rgst.style.opacity = 0;
  div.setAttribute("class", "form");
  bid.append(div);

  document.getElementById("cb").addEventListener("click", () => {
    bid.removeChild(div);
  });

  let forButton = document.getElementById("sb");
  forButton.addEventListener("click", () => {
    console.log("Its working..");
    let user = document.getElementById("userName").value;
    let password = document.getElementById("userPass").value;

    let auth = btoa(`${user}:${password}`);
    fetch("http://localhost:8080/travel/cusLogin", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Basic ${auth}`,
      },
    })
      .then((data) => {
        console.log(data);
        if (data.ok == false) {
          notify("Verification failed");
        } else return data.json();
      })
      .then((res) => {
        if (res != undefined) {
          localStorage.setItem("User", user);
          localStorage.setItem("Password", password);
          if (res.role == "ROLE_ADMIN") window.location.assign("admin.html");
          else if (res.role == "ROLE_USER") window.location.assign("user.html");
        }
      })
      .catch((error) => console.log(error));
  });
}
function reg() {
  rgst.style.opacity = 1;
}
btt.addEventListener("click", () => {
  rgst.style.opacity = 0;
});

function signup() {
  let name = document.getElementById("name");
  let password = document.getElementById("password");
  let address = document.getElementById("address");
  let aadhar = document.getElementById("aadhar");
  let gender = document.getElementById("gender");
  let country = document.getElementById("country");
  let role = document.getElementById("role");
  let moblie = document.getElementById("mobile");
  let email = document.getElementById("email");

  fetch("http://localhost:8080/travel/customer/signup", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: {
      customerName: name,
      customerPassword: password,
      address: address,
      aadharId: aadhar,
      gender: gender,
      country: country,
      role: role,
      mobileNo: moblie,
      email: email,
    },
  })
    .then((data) => {
      if (data.ok == false) notify("Registration failed");
      else return data.json();
    })
    .then((dat) => {
      if (dat != undefined) notify("Register successfully");
    });
}
