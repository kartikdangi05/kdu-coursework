import { useRef, useEffect } from 'react';

function Focus() {
  const firstInputRef = useRef<HTMLInputElement>(null);

  useEffect(() => {
    if(firstInputRef.current){
        firstInputRef.current.focus();
    }
  }, []);

  return (
    <form>
      <input type="text" ref={firstInputRef} />
      <input type="text" />
      <button type="submit">Submit</button>
    </form>
  );
}

export default Focus;
