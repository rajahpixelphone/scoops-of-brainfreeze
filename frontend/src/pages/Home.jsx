import { useEffect, useState } from 'react'
import { apiGet } from '../api'

function Home() {
  const [flavors, setFlavors] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')

  useEffect(() => {
    apiGet('/flavors')
      .then(data => setFlavors(data))
      .catch(err => setError(err.message))
      .finally(() => setLoading(false))
  }, [])

  if (loading) return <p>Loading flavors...</p>
  if (error) return <p style={{ color: 'red' }}>Error: {error}</p>

  return (
    <div>
      <h2>Our Delicious Flavors</h2>
      <p>Welcome to Scoops of Brainfreeze – the most "secure" ice cream shop in town 😉</p>

      <div className="flavor-grid">
        {flavors.map(flavor => (
          <div key={flavor.id} className="card">
            <h3>{flavor.name}</h3>
            <p>{flavor.description}</p>
            <p><strong>₹{flavor.price}</strong></p>
          </div>
        ))}
      </div>
    </div>
  )
}

export default Home
