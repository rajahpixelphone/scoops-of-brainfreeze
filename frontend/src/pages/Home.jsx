import { useEffect, useState } from 'react'

const API = 'http://localhost:8080/api'

function Home() {
  const [flavors, setFlavors] = useState([])

  useEffect(() => {
    fetch(`${API}/flavors`)
      .then(res => res.json())
      .then(data => setFlavors(data))
      .catch(err => console.error(err))
  }, [])

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
