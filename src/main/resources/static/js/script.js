

function goHome() {
  window.location.href = '/';
}

function profile() {
  window.location.href = '/profile';
}


const inWorkCompressor = document.getElementById('in_work');
const errorCompressor = document.getElementById('error_compressor');


function toggleErrorCompressor() {

    if (inWorkCompressor.checked) {
      errorCompressor.style.display = 'none';
      errorCompressor.value = ''; 
    } else {
      errorCompressor.style.display = 'inline-block';
    }
  }

  toggleErrorCompressor();

  inWorkCompressor.addEventListener('change', toggleErrorCompressor);