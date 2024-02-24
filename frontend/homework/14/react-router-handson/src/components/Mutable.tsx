import { useRef } from 'react';

function Mutable() {
  const myRef = useRef(0); 

  const updateRef = () => {
    myRef.current = myRef.current +  1;
    console.log(myRef.current);
  };

  return (
    <div>
      <p>Value: {myRef.current}</p>
      <button onClick={updateRef}>Increment</button>
    </div>
  );
}

export default Mutable;
