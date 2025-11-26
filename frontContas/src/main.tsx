import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import AppRoutes from './routes'
import { BrowserRouter } from 'react-router-dom'
import { Provider } from 'react-redux'
import { store } from './store/store'
import { PersistGate } from 'redux-persist/integration/react' // Importar PersistGate
import { persistor } from './store/store.ts'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <Provider store={store}>
      {/* PersistGate aguarda recuperar o token do session antes de renderizar */}
      <PersistGate loading={null} persistor={persistor}>
        <BrowserRouter>
          <AppRoutes/>
        </BrowserRouter> 
      </PersistGate>
    </Provider>
  </StrictMode>,
)
// src/main.tsx

