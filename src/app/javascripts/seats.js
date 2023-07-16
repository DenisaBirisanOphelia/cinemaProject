function generateSeats(rows, seatsPerRow) {
  let seatsHtml = '';
  for (let i = 1; i <= rows; i++) {
    seatsHtml += '<div class="row">';
    for (let j = 1; j <= seatsPerRow; j++) {
      seatsHtml += '<div class="seat">' + i + String.fromCharCode(64 + j) + '</div>';
    }
    seatsHtml += '</div>';
  }
  return seatsHtml;
}

