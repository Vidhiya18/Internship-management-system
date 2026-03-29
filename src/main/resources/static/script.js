// ================= NAVIGATION =================
function goSignup() {
    window.location.href = "signup.html";
}

function goLogin() {
    window.location.href = "login.html";
}

function goAdmin() {
    window.location.href = "admin.html";
}

function goMentor() {
    window.location.href = "mentor.html";
}

function goBack() {
    window.history.back();
}

// ================= SIGNUP =================
function signup() {

    const data = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    };

    fetch("http://localhost:8080/users/signup", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(res => res.text())
    .then(msg => {
        alert(msg);
        window.location.href = "login.html";
    })
    .catch(() => alert("Signup Error"));
}

// ================= LOGIN =================
function login() {

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    if (email === "" || password === "") {
        alert("Please enter all fields");
        return;
    }

    fetch("http://localhost:8080/users/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            email: email,
            password: password
        })
    })
    .then(response => response.text())
    .then(data => {

        if (data === "Login Success") {
            alert("Login Successful");
            window.location.href = "internship.html";
        } else {
            alert(data); // shows Invalid Credentials
        }

    })
    .catch(error => {
        console.error(error);
        alert("Server Error");
    });
}
// ================= INTERNSHIP =================
function applyInternship() {
    const data = {
        title: document.getElementById("title").value,
        domain: document.getElementById("domain").value,
        studentName: document.getElementById("studentName").value,
        email: document.getElementById("email").value,
        collegeName: document.getElementById("collegeName").value,
        startDate: document.getElementById("startDate").value,
        endDate: document.getElementById("endDate").value,
        duration: document.getElementById("duration").value,
        description: document.getElementById("description").value,
        status: "Pending"
    };

    fetch("http://localhost:8080/internships", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(() => alert("Internship Applied Successfully!"))
    .catch(() => alert("Error"));
}

// =================  ADD MENTOR =================
function addMentor() {
    const data = {
        mentorName: document.getElementById("name").value,
        email: document.getElementById("email").value,
        phoneNo: document.getElementById("phone").value
    };

    fetch("http://localhost:8080/mentors", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data)
    })
    .then(() => alert("Mentor Added"))
    .catch(() => alert("Error"));
}

// =================ASSIGN TASK =================
function assignTask() {
    const data = {
        studentName: document.getElementById("studentName").value,
        taskTitle: document.getElementById("taskTitle").value,
        description: document.getElementById("description").value
    };

    fetch("http://localhost:8080/tasks", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data)
    })
    .then(() => {
        alert("Task Assigned");
        loadTasks(); // auto refresh
    })
    .catch(() => alert("Error"));
}

// ================= LOAD TASKS =================
function loadTasks() {
    fetch("http://localhost:8080/tasks")
    .then(res => res.json())
    .then(data => {

        let table = "<tr><th>ID</th><th>Student</th><th>Task</th><th>Status</th></tr>";

        data.forEach(t => {
            table += `
            <tr>
                <td>${t.id}</td>
                <td>${t.studentName}</td>
                <td>${t.taskTitle}</td>
                <td>${t.status}</td>
            </tr>`;
        });

        document.getElementById("table").innerHTML = table;
    });
}
// ================= FEEDBACK =================
function giveFeedback() {
    const name = document.getElementById("feedbackStudent").value;
    const feedback = document.getElementById("feedbackText").value;

    alert("Feedback given to " + name + ": " + feedback);
}
// ================= ADMIN =================
function loadInternships() {
    fetch("http://localhost:8080/internships")
    .then(res => res.json())
    .then(data => {

        let table = "<tr><th>ID</th><th>Name</th><th>Domain</th><th>Status</th><th>Action</th></tr>";

        data.forEach(i => {
            table += `
            <tr>
                <td>${i.id}</td>
                <td>${i.studentName}</td>
                <td>${i.domain}</td>
                <td>${i.status}</td>
                <td>
                    <button onclick="updateInternship(${i.id}, 'Accepted')">Accept</button>
                    <button onclick="updateInternship(${i.id}, 'Rejected')">Reject</button>
                </td>
            </tr>`;
        });

        document.getElementById("table").innerHTML = table;
    });
}

// ================= UPDATE =================
function updateInternship(id, status) {
    fetch(`http://localhost:8080/internships/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ status: status })
    })
    .then(() => {
        alert("Updated!");
        loadInternships();
    })
    .catch(() => alert("Error"));
}