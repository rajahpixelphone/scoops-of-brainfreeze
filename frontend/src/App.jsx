import { BrowserRouter, Routes, Route, Link } from 'react-router-dom'
import Home from './pages/Home'
import Login from './pages/Login'
import Search from './pages/Search'
import Reviews from './pages/Reviews'
import Orders from './pages/Orders'

function App() {
  return (
    <BrowserRouter>
      <header>
        <div className="container">
          <h1>🍦 Scoops of Brainfreeze</h1>
          <nav>
            <Link to="/">Home</Link>
            <Link to="/search">Search Flavors</Link>
            <Link to="/reviews">Reviews</Link>
            <Link to="/orders">Orders</Link>
            <Link to="/login">Login</Link>
          </nav>
        </div>
      </header>

      <main className="container" style={{ paddingTop: '1.5rem' }}>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/search" element={<Search />} />
          <Route path="/reviews" element={<Reviews />} />
          <Route path="/orders" element={<Orders />} />
        </Routes>
      </main>
    </BrowserRouter>
  )
}

export default App
