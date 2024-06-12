const postsList = document.getElementById('posts-list');
const createBlogBtn = document.getElementById('create-blog-btn');

function openCreateBlogWindow() {
    console.info('Opening create blog window')
    window.open('create_blog.html', '_self');
  }

  if (createBlogBtn) {
    // Event listener for create blog button
    console.error('Adding event listener for create blog button')
    createBlogBtn.addEventListener('click', openCreateBlogWindow);
  } else {
    console.error("Element with ID 'create-blog-btn' could not be found");
  }

    // Function to display existing blog posts (same as before)
    function displayExistingBlogPosts() {
        const apiUrl = 'http://localhost:8080/v1/getAllBlog';
    
        fetch(apiUrl)
          .then(response => {
            if (!response.ok) {
              throw new Error('Network response was not ok');
            }
            return response.json();
          })
          .then(data => {
            // Clear existing blog posts before displaying new ones
            postsList.innerHTML = '';
            postsList.classList.add('flex-container');
            // Iterate over the response and display each blog post
            data.forEach(post => {
              console.log('post', post);
              const postDiv = document.createElement('div');
              postDiv.classList.add('blog-post');
              postDiv.style.margin = '10px';
              postDiv.innerHTML = `
              <a href="${post.blogUrl}" target="_blank" style="text-decoration: none; color: black;display: inline-block; border: 1px solid black;">
                <h3>${post.title}</h3>
                <img src="${post.imageUrl}" alt="${post.title}">
              </a>
            `;
              // Add an event listener to the div that opens the blog post in a new window when clicked
              postDiv.addEventListener('click', () => {
              window.open(post.blogUrl, '_blank');
              });
              postsList.appendChild(postDiv);
            });
          })
          .catch(error => {
            console.error('There was a problem fetching blog posts:', error);
            // Handle error scenario
          });
      }
    
      // Display existing blog posts on page load
      displayExistingBlogPosts();