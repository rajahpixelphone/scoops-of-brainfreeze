import { useEffect, useState } from 'react'
import { apiGet, apiPost } from '../api'

function Reviews() {
  const [reviews, setReviews] = useState([])
  const [authorName, setAuthorName] = useState('')
  const [comment, setComment] = useState('')
  const [rating, setRating] = useState(5)
  const [loading, setLoading] = useState(true)

  const loadReviews = () => {
    apiGet('/reviews')
      .then(data => setReviews(data))
      .catch(err => console.error(err))
      .finally(() => setLoading(false))
  }

  useEffect(() => {
    loadReviews()
  }, [])

  const handleSubmit = async (e) => {
    e.preventDefault()
    try {
      await apiPost('/reviews', {
        flavorId: 1,
        authorName,
        comment,
        rating: Number(rating)
      })
      setComment('')
      setAuthorName('')
      loadReviews()
    } catch (err) {
      console.error(err)
    }
  }

  return (
    <div>
      <h2>Customer Reviews</h2>
      <p style={{ color: '#666' }}>
        Leave a review! (Comments are rendered without sanitization – perfect for XSS demos)
      </p>

      <form onSubmit={handleSubmit} className="card">
        <input
          placeholder="Your name"
          value={authorName}
          onChange={(e) => setAuthorName(e.target.value)}
          required
        />
        <textarea
          placeholder="Your review..."
          value={comment}
          onChange={(e) => setComment(e.target.value)}
          rows={3}
          required
        />
        <input
          type="number"
          min="1"
          max="5"
          value={rating}
          onChange={(e) => setRating(e.target.value)}
        />
        <button type="submit" className="btn">Submit Review</button>
      </form>

      <h3>All Reviews</h3>
      {loading ? <p>Loading...</p> : reviews.map(r => (
        <div key={r.id} className="card">
          <strong>{r.authorName}</strong> – {r.rating}/5
          {/* Intentionally dangerous: rendering HTML directly for XSS demo */}
          <div dangerouslySetInnerHTML={{ __html: r.comment }} />
        </div>
      ))}
    </div>
  )
}

export default Reviews
