const API_URL = "http://localhost:8080";

const urlParams = new URLSearchParams(window.location.search);
const jobId = urlParams.get('id');

if (jobId) {
  // Prefill form for editing
  fetch(`${API_URL}/job/${jobId}`)
    .then(res => res.json())
    .then(job => {
      document.getElementById("postId").value = job.postId;
      document.getElementById("postProfile").value = job.postProfile;
      document.getElementById("postDesc").value = job.postDesc;
      document.getElementById("reqExperience").value = job.reqExperience;
      document.getElementById("postTechStack").value = job.postTechStack.join(", ");
      // Disable editing of Job ID on update (optional)
      document.getElementById("postId").disabled = true;
    })
    .catch(() => alert("Failed to load job details."));
}

document.getElementById("jobForm").addEventListener("submit", function (e) {
  e.preventDefault();

  const job = {
    postId: parseInt(document.getElementById("postId").value),
    postProfile: document.getElementById("postProfile").value,
    postDesc: document.getElementById("postDesc").value,
    reqExperience: parseInt(document.getElementById("reqExperience").value),
    postTechStack: document.getElementById("postTechStack").value.split(",").map(s => s.trim())
  };

  const method = jobId ? 'PUT' : 'POST';

  fetch(`${API_URL}/job`, {
    method,
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(job)
  })
    .then(res => {
      if (!res.ok) throw new Error("Failed to save job");
      return res.json();
    })
    .then(data => {
      alert("Job saved successfully!");
      // Redirect to home page after submission
      window.location.href = "index.html";
    })
    .catch(err => alert("Error saving job: " + err));
});
