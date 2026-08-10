import { useState } from 'react'
import { API_BASE } from '../api'

function Orders() {
  const [orderId, setOrderId] = useState('')
  const [order, setOrder] = useState(null)
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(false)

  const fetchOrder = async (e) => {
    e.preventDefault()
    setError('')
    setOrder(null)
    setLoading(true)
    try {
      const res = await fetch(`${API_BASE}/orders/${orderId}`)
      if (!res.ok) {
        setError('Order not found')
        return
      }
      const data = await res.json()
      setOrder(data)
    } catch (err) {
      setError('Failed to fetch order')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div>
      <h2>View Order (IDOR Demo)</h2>
      <p style={{ color: '#666' }}>
        Enter any order ID. There is no ownership check – classic Broken Access Control.
      </p>

      <form onSubmit={fetchOrder} className="card">
        <input
          type="number"
          placeholder="Order ID"
          value={orderId}
          onChange={(e) => setOrderId(e.target.value)}
        />
        <button type="submit" className="btn" disabled={loading}>
          {loading ? 'Loading...' : 'Fetch Order'}
        </button>
      </form>

      {error && <div className="card" style={{ color: 'red' }}>{error}</div>}

      {order && (
        <div className="card">
          <h3>Order #{order.id}</h3>
          <p>User ID: {order.userId}</p>
          <p>Flavor ID: {order.flavorId}</p>
          <p>Quantity: {order.quantity}</p>
          <p>Notes: {order.notes}</p>
          <p>Total: ₹{order.totalPrice}</p>
          <p>Status: {order.status}</p>
        </div>
      )}
    </div>
  )
}

export default Orders
