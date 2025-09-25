import LayoutAuth from "./components/LayoutAuth/index.tsx";
import LayoutMain from "./components/LayoutMain/index.tsx";
import Login from "./paginas/login";
import Cadastrese from "./paginas/cadastrese";
import Home from "./paginas/home/home.tsx";
import { Navigate, Route, Routes } from "react-router-dom";

function App() {
  return (
    <Routes>
      {/* Redireciona raiz para login */}
      <Route path="/" element={<Navigate to="/auth/login" />} />

      <Route path="/auth" element={<LayoutAuth />}>
        <Route path="login" element={<Login />} />
        <Route path="cadastrese" element={<Cadastrese />} />
      </Route>

      <Route path="/" element={<LayoutMain />}>
        <Route path="home" element={<Home />} />
        {/* outras rotas logadas, ex: usuarios, contas... */}
      </Route>
    </Routes>
  );
}

export default App;