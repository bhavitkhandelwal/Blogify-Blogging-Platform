document.addEventListener('DOMContentLoaded', function() {
  const blogForm = document.getElementById('blog-form');
  const postsList = document.getElementById('posts-list');
  const createBlogBtn = document.getElementById('create-blog-btn');

  // Function to open new window for creating blog
  function openCreateBlogWindow() {
    console.info('Opening create blog window')
    window.open('create_blog.html', '_self');
  }

  if (createBlogBtn) {
    // Event listener for create blog button
    createBlogBtn.addEventListener('click', openCreateBlogWindow);
  } else {
    console.error("Element with ID 'create-blog-btn' could not be found");
  }

  // Function to save blog post (using API call)
  function saveBlogPost(title, content, url, author, tags) {
    console.log('title', title);
    const apiUrl = 'http://localhost:8080/v1/createBlog';
    const requestBody = {
      title: title,
      content: content,
      author: author,
      imageUrl: url,
      tags: tags,
      blogStatus: "Published", // You can customize these values as needed
      blogType: "Type1",
      blogCategory: "Category1",
      blogSubCategory: "SubCategory1",
      blogBody: "This is the body of the sample blog."
    };

    fetch(apiUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(requestBody)
    })
    .then(response => {
      console.log('response', response);
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      // Redirect to home page after successful save
      window.location.href = 'index.html';
      window.open('index.html', '_self');
    })
    .catch(error => {
      console.error('There was a problem saving the blog post:', error);
      // Handle error scenario
    });
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
          <a href="${post.url}" target="_blank" style="text-decoration: none; color: black;display: inline-block; border: 1px solid black;">
            <h3>${post.title}</h3>
            <img src="${post.imageUrl}" alt="${post.title}">
          </a>
        `;
          // Add an event listener to the div that opens the blog post in a new window when clicked
          postDiv.addEventListener('click', () => {
          window.open(post.url, '_blank');
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

  // Event listener for blog form submission
  blogForm.addEventListener('submit', function(event) {
    event.preventDefault();
    console.info('Form submitted');
    const title = this.title.value;
    const content = this.content.value;
    const url = this.url.value;
    const author = "Your Name"; // You can replace this with actual author data
    const tags = "tag1, tag2, tag3"; // You can replace this with actual tag data
    saveBlogPost(title, content, url, author, tags);
    this.reset();
    window.open('index.html', '_self');
  });
});
