import { useState } from 'react'

const API = 'http://localhost:8080/api'

function Search() {
  const [query, setQuery] = useState('')
  const [results, setResults] = useState([])
  const [searched, setSearched] = useState(false)

  const handleSearch = async (e) => {
    e.preventDefault()
    setSearched(true)
    try {
      const res = await fetch(`${API}/flavors/search?q=${encodeURIComponent(query)}`)
      const data = await res.json()
      setResults(data)
    } catch (err) {
      console.error(err)
      setResults([])
    }
  }

  return (
    <div>
      <h2>Search Flavors</h2>
      <p style={{ color: '#666', fontSize: '0.95rem' }}>
        Try searching normally, or experiment with payloads like <code>' OR '1'='1</code>
      </p>

      <form onSubmit={handleSearch} className="card">
        <input
          type="text"
          placeholder="Search ice cream flavors..."
          value={query}
          onChange={(e) => setQuery(e.target.value)}
        />
        <button type="submit" className="btn">Search</button>
      </form>

      {searched && (
        <div>
          <h3>Results ({results.length})</h3>
          {results.length === 0 ? (
            <p>No flavors found.</p>
          ) : (
            results.map(f => (
              <div key={f.id} className="card">
                <strong>{f.name}</strong> – ₹{f.price}
                <p>{f.description}</p>
              </div>
            ))
          )}
        </div>
      )}
    </div>
  )
}

export default Search
