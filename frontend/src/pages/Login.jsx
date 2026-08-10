import { useState } from 'react'

const API = 'http://localhost:8080/api'

function Login() {
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [message, setMessage] = useState('')

  const handleLogin = async (e) => {
    e.preventDefault()
    try {
      const res = await fetch(`${API}/auth/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
      })
      const data = await res.json()
      if (data.success) {
        setMessage(`Welcome ${data.fullName}! Role: ${data.role}`)
        localStorage.setItem('user', JSON.stringify(data))
      } else {
        setMessage(data.message || 'Login failed')
      }
    } catch (err) {
      setMessage('Error connecting to server')
    }
  }

  return (
    <div>
      <h2>Login</h2>
      <p style={{ color: '#666' }}>Try admin / softserve123 or student / password</p>

      <form onSubmit={handleLogin} className="card">
        <input
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button type="submit" className="btn">Login</button>
      </form>

      {message && <div className="card">{message}</div>}
    </div>
  )
}

export default Login
