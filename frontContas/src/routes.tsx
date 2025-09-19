
import AuthLayout from "./components/LayoutAdmin";
import Login from "./paginas/login";
import Cadastrese from "./paginas/cadastrese";
import Home from "./paginas/home/home.tsx";
import { Navigate, Route, Routes } from "react-router-dom";


function App() {
  return (
      <Routes>
        {/* Layout de autenticação */}
        <Route path="/auth" element={<AuthLayout />}>
          <Route index element={<Navigate to="/login" />} />

          <Route path="login" element={<Login />} />
          <Route path="cadastrese" element={<Cadastrese />} />
        </Route>


        <Route path="/" element={<AuthLayout />}>
          <Route path="home" element={<Home />} />
        </Route>

      </Routes>
  );
}

export default App;
