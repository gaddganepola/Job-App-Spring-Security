const API_URL = "http://localhost:8080";

function fetchAllJobs() {
  fetch(`${API_URL}/jobs`)
    .then(res => res.json())
    .then(jobs => {
      const container = document.getElementById("jobCards");
      container.innerHTML = "";

      if (jobs.length === 0) {
        container.innerHTML = `<p class="text-center text-muted fs-5">No jobs available.</p>`;
        return;
      }

      jobs.forEach(job => {
        const card = document.createElement("div");
        card.className = "col";

        card.innerHTML = `
          <div class="card h-100 shadow-sm">
            <div class="card-body d-flex flex-column">
              <h5 class="card-title text-primary">${job.postProfile}</h5>
              <p class="card-text flex-grow-1">${job.postDesc.length > 120 ? job.postDesc.slice(0, 117) + "..." : job.postDesc}</p>
              <p class="mb-1"><strong>Experience:</strong> ${job.reqExperience} years</p>
              <p class="mb-3"><strong>Tech Stack:</strong> ${job.postTechStack.join(", ")}</p>
              <div class="mt-auto d-flex justify-content-between">
                <button class="btn btn-sm btn-primary" onclick="editJob(${job.postId})">Edit</button>
                <button class="btn btn-sm btn-danger" onclick="deleteJob(${job.postId})">Delete</button>
              </div>
            </div>
          </div>
        `;

        container.appendChild(card);
      });
    })
    .catch(err => {
      console.error("Failed to fetch jobs:", err);
      const container = document.getElementById("jobCards");
      container.innerHTML = `<p class="text-danger text-center">Failed to load jobs.</p>`;
    });
}

function deleteJob(id) {
  if (confirm("Are you sure you want to delete this job?")) {
    fetch(`${API_URL}/job/${id}`, { method: "DELETE" })
      .then(res => res.text())
      .then(msg => {
        alert(msg);
        fetchAllJobs();
      });
  }
}

function editJob(id) {
  window.location.href = `add.html?id=${id}`;
}

// Initial load
fetchAllJobs();
