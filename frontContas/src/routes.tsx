import AuthLayout from "./components/LayoutAdmin";
import Login from "./paginas/login";
import Cadastrese from "./paginas/cadastrese";
import Home from "./paginas/home/home.tsx";
import { Navigate, Route, Routes } from "react-router-dom";

function App() {
  return (
      <Routes>
        <Route path="/auth" element={<AuthLayout />}>
          <Route index element={<Navigate to="/auth/login" />} />

          <Route path="login" element={<Login />} />
          <Route path="cadastrese" element={<Cadastrese />} />
        </Route>


          <Route path="home" element={<Home />} />

      </Routes>
  );
}

export default App;