const blogForm = document.getElementById('blog-form');

function generateUUID() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random() * 16 | 0,
            v = c === 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
}
  
  // Function to save blog post (using API call)
  function saveBlogPost(title, content, imageUrl, author, tags) {
    console.log('title', title);
    const apiUrl = 'http://localhost:8080/v1/createBlog';
    const blogId = generateUUID();
    const requestBody = {
      title: title,
      content: content,
      author: author,
      imageUrl: imageUrl,
      tags: tags,
      blogId: blogId,
      blogStatus: "Published", // You can customize these values as needed
      blogType: "Type1",
      blogCategory: "Category1",
      blogSubCategory: "SubCategory1",
      blogBody: "This is the body of the sample blog.",
      blogUrl: `http://viewblog.html?id=${blogId}`
    };
    console.error('requestBody', requestBody);
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

blogForm.addEventListener('submit', function(event) {
    event.preventDefault();
    console.info('Form submitted');
    const title = this.title.value;
    const content = this.content.value;
    const imageUrl = this.url.value;
    const author = "Your Name"; // You can replace this with actual author data
    const tags = "tag1, tag2, tag3"; // You can replace this with actual tag data
    saveBlogPost(title, content, imageUrl, author, tags);
    this.reset();
    window.open('index.html', '_self');
  });