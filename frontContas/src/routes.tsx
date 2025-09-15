
import AuthLayout from "./components/LayoutAdmin";
import Login from "./paginas/login";
import Cadastrese from "./paginas/cadastrese";
import Home from "./paginas/home";
import { Navigate, Route, Routes } from "react-router-dom";


function App() {
  return (
      <Routes>
        {/* Layout de autenticação */}
        <Route path="/" element={<AuthLayout />}>
          {/* Redireciona "/" para "/login" */}
          <Route index element={<Navigate to="/login" />} />

          {/* Rotas dentro do layout */}
          <Route path="login" element={<Login />} />
          <Route path="cadastrese" element={<Cadastrese />} />
        </Route>

        <Route path="/" element={<AuthLayout />}>
          <Route index element={<Home />} /> {/* "/" carrega Home direto */}
          <Route path="login" element={<Login />} />
          <Route path="cadastrese" element={<Cadastrese />} />
        </Route>

      </Routes>
  );
}

export default App;
