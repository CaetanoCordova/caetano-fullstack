import LayoutAuth from "./components/LayoutAuth/index.tsx";
import LayoutMain from "./components/LayoutMain/index.tsx";
import Login from "./paginas/login";
import Cadastrese from "./paginas/cadastrese";
import { Navigate, Route, Routes } from "react-router-dom";
import Contas from "./paginas/contas/contas.tsx";
import Usuarios from "./paginas/usuarios/usuarios.tsx";
import ContasEditar from "./paginas/contas/contasEditar.tsx";
import ContasCriar from "./paginas/contas/contasCriar.tsx";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Navigate to="/auth/login" />} />

      <Route path="/auth" element={<LayoutAuth />}>
        <Route path="login" element={<Login />} />
        <Route path="cadastrese" element={<Cadastrese />} />
      </Route>

      <Route path="/home" element={<LayoutMain />}>
        <Route path="contas" element={<Contas />} />
        <Route path="contas" element={<ContasCriar />} />
        <Route path="contas/:id/editar" element={<ContasEditar />} />
        <Route path="usuarios" element={<Usuarios />} />
      </Route>
    </Routes>
  );
}

export default App;