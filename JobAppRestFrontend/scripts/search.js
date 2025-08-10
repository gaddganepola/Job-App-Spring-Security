const API_URL = "http://localhost:8080";

function searchJobs() {
  const keyword = document.getElementById("searchKeyword").value.trim();
  if (!keyword) return;

  fetch(`${API_URL}/jobs/keyword/${encodeURIComponent(keyword)}`)
    .then(res => res.json())
    .then(jobs => {
      const resultsDiv = document.getElementById("searchResults");
      resultsDiv.innerHTML = "";
      if (jobs.length === 0) {
        resultsDiv.innerHTML = "<p class='text-danger'>No jobs found for that keyword.</p>";
        return;
      }
      jobs.forEach(job => {
        const card = `
          <div class="col">
            <div class="card h-100">
              <div class="card-body">
                <h5 class="card-title">${job.postProfile}</h5>
                <p class="card-text"><strong>Description:</strong> ${job.postDesc}</p>
                <p class="card-text"><strong>Experience:</strong> ${job.reqExperience} years</p>
                <p class="card-text"><strong>Tech Stack:</strong> ${job.postTechStack.join(", ")}</p>
              </div>
            </div>
          </div>
        `;
        resultsDiv.innerHTML += card;
      });
    })
    .catch(err => alert("Search failed: " + err));
}
