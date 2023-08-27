let pops = document.getElementById("pop");
let inp = document.getElementById("inp");

function openpopup() {
  let email = document.getElementById("inp").value;
  fetch("http://localhost:8080/travel/subscriber", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      email: email,
    }),
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
let timeout;
function notify(message) {
  window.clearTimeout(timeout);
  nid.innerHTML = message;
  nid.style.opacity = 1;
  timeout = setInterval(() => {
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

    if (user == "" || user == " ") notify("Username is mandatory");
    else if (password == "" || password == " ") notify("Password is mandatory");
    else {
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
            bid.removeChild(div);
            localStorage.setItem("User", user);
            localStorage.setItem("Password", password);
            if (res.role == "ROLE_ADMIN")
              window.location.assign("HTML/admin.html");
            else if (res.role == "ROLE_USER")
              window.location.assign("HTML/user.html");
          }
        })
        .catch((error) => console.log(error));
    }
  });
}
function reg() {
  rgst.style.opacity = 1;
}
btt.addEventListener("click", () => {
  rgst.style.opacity = 0;
});

function signup() {
  let name = document.getElementById("name").value;
  let password = document.getElementById("password").value;
  let address = document.getElementById("address").value;
  let aadhar = document.getElementById("aadhar").value;
  let gender = document.getElementById("gender").value;
  let country = document.getElementById("country").value;
  let role = document.getElementById("role").value;
  let moblie = document.getElementById("mobile").value;
  let email = document.getElementById("email").value;
  if (name == "" || name == " ") notify("Name is mandatory");
  else if (password == "" || password == " ") notify("Password is mandatory");
  else if (address == "" || address == " ") notify("Aadress is mandatory");
  else if (aadhar == "" || aadhar == " ") notify("Aadhar is mandatory");
  else if (gender == "" || gender == " ") notify("Gender is mandatory");
  else if (country == "" || country == " ") notify("Country is mandatory");
  else if (moblie == "" || moblie == " ") notify("Mobile number is mandatory");
  else if (email == "" || email == " ") notify("Email is mandatory");
  else {
    fetch("http://localhost:8080/travel/customer/signup", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        customerName: name,
        customerPassword: password,
        address: address,
        aadharId: aadhar,
        gender: gender,
        country: country,
        role: role,
        mobileNo: moblie,
        email: email,
      }),
    })
      .then((data) => {
        console.log(data);
        if (data.message == "Customer already registered")
          notify("Customer already registered");
        else if (data.ok == false) notify("Registration failed");
        else return data.json();
      })
      .then((dat) => {
        if (dat.customerId != undefined) {
          rgst.style.opacity = 0;
          notify("Register successfully");
        }
      });
  }
}
